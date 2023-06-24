package jg.sh.parsing.nodes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jg.sh.common.Location;
import jg.sh.parsing.Context;
import jg.sh.parsing.Visitor;
import jg.sh.parsing.token.TokenType;

/**
 * Declares a variable in the current scope.
 * 
 * Format:
 * 
 * var (varNam [:= expr],)+;
 * 
 * or 
 * 
 * const (varName := expr,)+;
 */
public class VarDeclr extends Node {

  private final Identifier name;
  private final boolean isConst;
  private final Node initialValue;
  private final Set<Keyword> descriptors;

  public VarDeclr(Identifier name, boolean isConst, Node initialValue, Location start, Location end, Keyword ... descriptors) {
    super(start, end);
    this.name = name;
    this.isConst = isConst;
    this.initialValue = initialValue;
    this.descriptors = new HashSet<>(Arrays.asList(descriptors));
  }

  public boolean isConst() {
    return isConst;
  }

  public Identifier getName() {
    return name;
  }

  public Node getInitialValue() {
    return initialValue;
  }

  public Set<Keyword> getDescriptors() {
    return descriptors;
  }

  public boolean hasDescriptor(Keyword keyword) {
    return descriptors.contains(keyword);
  }

  public boolean hasDescriptor(TokenType keyword) {
    return descriptors.stream().anyMatch(x -> x.getKeyword() == keyword);
  }

  @Override
  public <T, C extends Context> T accept(Visitor<T, C> visitor, C parentContext) {
    return visitor.visitVarDeclr(parentContext, this);
  }

  @Override
  public String repr() {
    throw new UnsupportedOperationException("Unimplemented method 'repr'");
  }

  @Override
  public boolean isLValue() {
    return true;
  }
  
}