package jg.sh.runtime.objects.literals;

import jg.sh.common.FunctionSignature;
import jg.sh.modules.builtin.SystemModule;
import jg.sh.runtime.exceptions.InvocationException;
import jg.sh.runtime.loading.RuntimeModule;
import jg.sh.runtime.objects.ArgVector;
import jg.sh.runtime.objects.RuntimeInstance;
import jg.sh.runtime.objects.callable.Callable;
import jg.sh.runtime.objects.callable.ImmediateInternalCallable;
import jg.sh.runtime.objects.callable.InternalFunction;
import jg.sh.runtime.objects.callable.RuntimeInternalCallable;
import jg.sh.runtime.threading.fiber.Fiber;

public class RuntimeInteger extends RuntimePrimitive {
  
  private static final InternalFunction ADD = new InternalFunction(FunctionSignature.ONE_ARG) {
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateInt(self.value + otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateFloat(self.value + otherFloat.getValue());
      }
      
      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction SUB = new InternalFunction(FunctionSignature.ONE_ARG) {  
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateInt(self.value - otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateFloat(self.value - otherFloat.getValue());
      }

      throw new InvocationException("Unsupported operand on subtraction!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction MUL = new InternalFunction(FunctionSignature.ONE_ARG) {  
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateInt(self.value * otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateFloat(self.value * otherFloat.getValue());
      }

      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction DIV = new InternalFunction(FunctionSignature.ONE_ARG) {  
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateInt(self.value / otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateFloat(self.value / otherFloat.getValue());
      }

      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction MOD = new InternalFunction(FunctionSignature.ONE_ARG) {  
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateInt(self.value % otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateFloat(self.value % otherFloat.getValue());
      }

      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction NEG = new InternalFunction(FunctionSignature.NO_ARG) {  
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      return executor.getHeapAllocator().allocateInt(-self.value);
    }
  };

  private static final InternalFunction LESS = new InternalFunction(FunctionSignature.ONE_ARG) {
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value < otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value < otherFloat.getValue());
      }

      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction GREAT = new InternalFunction(FunctionSignature.ONE_ARG) {
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value > otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value > otherFloat.getValue());
      }

      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction LESSE = new InternalFunction(FunctionSignature.ONE_ARG) {
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value <= otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value <= otherFloat.getValue());
      }

      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction GREATE = new InternalFunction(FunctionSignature.ONE_ARG) {
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value >= otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value >= otherFloat.getValue());
      }

      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };

  private static final InternalFunction EQUAL = new InternalFunction(FunctionSignature.ONE_ARG) {     
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value == otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value == otherFloat.getValue());
      }
      return executor.getHeapAllocator().allocateBool(false);
    }
  };

  private static final InternalFunction NOT_EQUAL = new InternalFunction(FunctionSignature.ONE_ARG) {     
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value != otherInt.getValue());
      }
      else if (otherOperand instanceof RuntimeFloat) {
        RuntimeFloat otherFloat = (RuntimeFloat) otherOperand;
        return executor.getHeapAllocator().allocateBool(self.value != otherFloat.getValue());
      }
      return executor.getHeapAllocator().allocateBool(false);
    }
  };
  
  private static final InternalFunction BAND = new InternalFunction(FunctionSignature.ONE_ARG) {
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateInt(self.value & otherInt.value);
      }
      
      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };
  
  private static final InternalFunction BOR = new InternalFunction(FunctionSignature.ONE_ARG) {
    @Override
    public RuntimeInstance invoke( Fiber executor, ArgVector args) throws InvocationException {
      RuntimeInteger self = (RuntimeInteger) args.getPositional(SELF_INDEX);
      RuntimeInstance otherOperand = args.getPositional(ARG_INDEX);
      if (otherOperand instanceof RuntimeInteger) {
        RuntimeInteger otherInt = (RuntimeInteger) otherOperand;
        return executor.getHeapAllocator().allocateInt(self.value | otherInt.value);
      }
      
      throw new InvocationException("Unsupported operand on addition!", (Callable) args.getPositional(0));
    }
  };
  
  
  private final long value;
  
  public RuntimeInteger(long value) {
    this.value = value;

    final RuntimeModule systemModule =  SystemModule.getNativeModule().getModule();

    attributes.put(FuncOperatorCoupling.ADD.getFuncName(), new ImmediateInternalCallable(systemModule, this, ADD));
    attributes.put(FuncOperatorCoupling.SUB.getFuncName(), new ImmediateInternalCallable(systemModule, this, SUB));
    attributes.put(FuncOperatorCoupling.MUL.getFuncName(), new ImmediateInternalCallable(systemModule, this, MUL));
    attributes.put(FuncOperatorCoupling.DIV.getFuncName(), new ImmediateInternalCallable(systemModule, this, DIV));
    attributes.put(FuncOperatorCoupling.MOD.getFuncName(), new ImmediateInternalCallable(systemModule, this, MOD));
    attributes.put(FuncOperatorCoupling.NEG.getFuncName(), new ImmediateInternalCallable(systemModule, this, NEG));
    attributes.put(FuncOperatorCoupling.LESS.getFuncName(), new ImmediateInternalCallable(systemModule, this, LESS));
    attributes.put(FuncOperatorCoupling.GREAT.getFuncName(), new ImmediateInternalCallable(systemModule, this, GREAT));
    attributes.put(FuncOperatorCoupling.LESSE.getFuncName(), new ImmediateInternalCallable(systemModule, this, LESSE));
    attributes.put(FuncOperatorCoupling.GREATE.getFuncName(), new ImmediateInternalCallable(systemModule, this, GREATE));
    attributes.put(FuncOperatorCoupling.EQUAL.getFuncName(), new ImmediateInternalCallable(systemModule, this, EQUAL));
    attributes.put(FuncOperatorCoupling.NOTEQUAL.getFuncName(), new ImmediateInternalCallable(systemModule, this, NOT_EQUAL));
    attributes.put(FuncOperatorCoupling.BAND.getFuncName(), new ImmediateInternalCallable(systemModule, this, BAND));
    attributes.put(FuncOperatorCoupling.BOR.getFuncName(), new ImmediateInternalCallable(systemModule, this, BOR));
  }

  public long getValue() {
    return value;
  }
  
  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
