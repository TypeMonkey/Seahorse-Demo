package jg.sh.runtime.threading.thread;

import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jg.sh.common.FunctionSignature;
import jg.sh.modules.builtin.SystemModule;
import jg.sh.runtime.alloc.Cleaner;
import jg.sh.runtime.alloc.HeapAllocator;
import jg.sh.runtime.exceptions.InvocationException;
import jg.sh.runtime.loading.ModuleFinder;
import jg.sh.runtime.objects.ArgVector;
import jg.sh.runtime.objects.RuntimeNull;
import jg.sh.runtime.objects.callable.Callable;
import jg.sh.runtime.objects.callable.ImmediateInternalCallable;
import jg.sh.runtime.objects.callable.InternalFunction;
import jg.sh.runtime.threading.ThreadManager;
import jg.sh.runtime.threading.fiber.Fiber;
import jg.sh.runtime.threading.fiber.FiberStatus;
import jg.sh.runtime.threading.frames.StackFrame;


/**
 * A thread of execution.
 * 
 * Unlike a {@link Fiber}, a RuntimeThread is backed by a {@link java.lang.Thread} 
 * which - when {@link start} is called - will execute it without waiting on 
 * the Seahorse threadpool. 
 */
public class RuntimeThread extends Fiber {

  private static Logger LOG = LogManager.getLogger(RuntimeThread.class);

  private static final InternalFunction START = InternalFunction.create(
    RuntimeThread.class,
    FunctionSignature.NO_ARG, 
    (fiber, self, callable, args) -> {
      final RuntimeThread thread = (RuntimeThread) self;
      thread.start();
      return RuntimeNull.NULL;
    }
  );
  
  private final Thread thread;
  private final Consumer<Fiber> fiberReporter;

  public RuntimeThread(HeapAllocator allocator, 
                       ModuleFinder finder, 
                       Cleaner cleaner, 
                       ThreadManager manager,
                       Consumer<Fiber> fiberReporter) {
    this(allocator, finder, cleaner, manager, null, fiberReporter);
  }

  /**
   * Constructs a RuntimeThread
   * @param allocator - the HeapAllocator to use for object allocation
   * @param finder - the ModuleFinder to use for loading modules
   * @param cleaner - the Cleaner to use for garbage collection
   * @param manager - the ThreadManager this RuntimeThread is managed by
   * @param callable - the function to run on this thread
   * @param args - the arguments for the provided function
   * @param fiberReporter - the function to use to report that status of this RuntimeThread
   */
  public RuntimeThread(HeapAllocator allocator, 
                       ModuleFinder finder, 
                       Cleaner cleaner, 
                       ThreadManager manager, 
                       StackFrame initialFrame,
                       Consumer<Fiber> fiberReporter) {
    super(allocator, finder, manager, cleaner, (ini, self) -> {
      ini.init("start", new ImmediateInternalCallable(SystemModule.getNativeModule().getModule(), self, START));
    });
    this.thread = new Thread(this::startInternal);
    this.fiberReporter = fiberReporter;    
    queue(initialFrame);
  }
  
  /**
   * Executes the frames of this RuntimeThread
   * to completion.
   */
  private void startInternal() {
    try {
      setStatus(FiberStatus.RUNNING);
      while (hasFrame()) {
        advanceFrame();
      }

      markEndTime();
      setStatus(hasLeftOverException() ? FiberStatus.TERMINATED : FiberStatus.COMPLETED);

      if (hasLeftOverException()) {
        LOG.info("--- CAUGHT ERROR ");
        getLeftOverException().printStackTrace();
      }
    } catch (Exception e) {
      setStatus(FiberStatus.TERMINATED);
      System.err.println("Uncaught error: ");
      e.printStackTrace();
    }

    fiberReporter.accept(this);
  }
  
  /**
   * Starts this RuntimeThread
   */
  public void start() {
    thread.start();
  }
  
}
