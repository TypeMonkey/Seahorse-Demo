package jg.sh.compile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jg.sh.compile.exceptions.ValidationException;
import jg.sh.compile.instrs.Instruction;

public class NodeResult {
  
  private final List<ValidationException> exceptions;
  private final List<Instruction> instructions;

  private NodeResult(List<ValidationException> exceptions, List<Instruction> instructions) {
    this.exceptions = exceptions;
    this.instructions = instructions;
  }

  public boolean hasExceptions() {
    return !exceptions.isEmpty();
  }

  public List<ValidationException> getExceptions() {
    return exceptions;
  }

  public List<Instruction> getInstructions() {
    return instructions;
  }

  public static NodeResult invalid(ValidationException ... exceptions) {
    return new NodeResult(Arrays.asList(exceptions), Collections.emptyList());
  }

  public static NodeResult invalid(List<ValidationException> exceptions) {
    return new NodeResult(exceptions, Collections.emptyList());
  }

  public static NodeResult valid(Instruction ... exceptions) {
    return new NodeResult(Collections.emptyList(), Arrays.asList(exceptions));
  }

  public static NodeResult valid(List<Instruction> exceptions) {
    return new NodeResult(Collections.emptyList(), exceptions);
  }
}
