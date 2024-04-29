package com.deloitte.java.sonarcustomrule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.sonar.api.SonarRuntime;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

import com.deloitte.java.sonarcustomrule.rules.TimeLimiterAnnotationMandatoryRule;

public class JavaRulesDefinition implements RulesDefinition {

	private static final String RESOURCE_BASE_PATH = "org/sonar/l10n/java/rules/java";

	public static final String REPOSITORY_KEY = "lucasnscr/Resilience-Patterns";
	public static final String REPOSITORY_NAME = "Resilience-Patterns";

	private static final Logger LOGGER = Loggers.get(JavaRulesDefinition.class);

	// Add the rule keys of the rules which need to be considered as template-rules
	private static final Set<String> RULE_TEMPLATES_KEY = Collections.emptySet();

	private final SonarRuntime runtime;

	public JavaRulesDefinition(SonarRuntime runtime) {
		this.runtime = runtime;
	}

	@Override
	public void define(Context context) {

		NewRepository repository = context.createRepository(REPOSITORY_KEY, "java").setName(REPOSITORY_NAME);
		LOGGER.debug(REPOSITORY_KEY + "repo : " + repository);
		RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(RESOURCE_BASE_PATH, runtime);

		ruleMetadataLoader.addRulesByAnnotatedClass(repository, new ArrayList<>(RulesList.getChecks()));

		setTemplates(repository);

		repository.done();
	}

	private static void setTemplates(NewRepository repository) {
		RULE_TEMPLATES_KEY.stream().map(repository::rule).filter(Objects::nonNull)
				.forEach(rule -> rule.setTemplate(true));
	}
}
