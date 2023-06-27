package jg.sh.parsing.nodes.statements;

import java.util.Map;

import jg.sh.common.Location;
import jg.sh.parsing.nodes.Identifier;

/**
 * A use declaration is a top-level only statement that 
 * intakes another module (or its functions, data definitions and variables) for use inside a module.
 * 
 * Use declarations are of the following format:
 *      use <module name> ;
 * or
 *      use <module name>:<module component name> (, <module component name>)* ;
 * 
 * Example: 
 *      use ModuleOne;  //Imports the module named "ModuleOne"
 *      use ModuleOne:funky; //Imports the module component from "ModuleOne" called "funky", but doesn't import ModuleOne
 *      use ModuleOne::funky, boo, bar; //Imports the funky, boo, and bar components from ModuleOne
 * 
 * Alias - Use declaration can append an alias as to not conflict with any equally-named component
 *         in the module:
 *  
 *      use ModuleOne as Mod; //ModuleOne now has the alias of "Mod" in the module
 *      use ModuleOne:funky as func; //funky can be now be used as "func"
 *      use ModuleOne:funky as fy, boo as apple; //funky can be used as "fy" and boo as "apple"
 */
public class UseStatement extends Statement {

  private final Identifier moduleName;
  private final Identifier moduleAlias;
  private final Map<Identifier, Identifier> compAliasMap;

  public UseStatement(Identifier moduleName, 
                      Identifier alias, 
                      Map<Identifier, Identifier> compAliasMap, 
                      Location start, 
                      Location end) {
    super(start, end);
    this.moduleName = moduleName;
    this.compAliasMap = compAliasMap;
    this.moduleAlias = alias;
  }

  public Identifier getModuleAlias() {
    return moduleAlias;
  }

  public Map<Identifier, Identifier> getCompAliasMap() {
    return compAliasMap;
  }

  public Identifier getModuleName() {
    return moduleName;
  }
  
}
