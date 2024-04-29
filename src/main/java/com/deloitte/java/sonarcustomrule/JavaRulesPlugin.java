package com.deloitte.java.sonarcustomrule;

import org.sonar.api.Plugin;

/**
 * Entry point of your plugin containing your custom rules
 */
public class JavaRulesPlugin implements Plugin {

  @Override
  public void define(Context context) {
    // server extensions -> objects are instantiated during server startup
    context.addExtension(JavaRulesDefinition.class);

    // batch extensions -> objects are instantiated during code analysis
    context.addExtension(MyJavaFileCheckRegistrar.class);

  }

}
