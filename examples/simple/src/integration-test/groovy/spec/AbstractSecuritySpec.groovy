package spec

import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.testing.mixin.integration.Integration
import spock.lang.Stepwise

@Integration
@Stepwise
abstract class AbstractSecuritySpec extends GebReportingSpec {

	void setup() {
		if ( hasProperty('serverPort') ) {
			browser.baseUrl = "http://localhost:${getProperty('serverPort')}/"
		} else {
			browser.baseUrl = 'http://localhost:8080/'
		}
		logout()
	}

	void cleanup() {
		CachingDriverFactory.clearCache()
	}

	protected void logout() {
		String url = SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
		go url
		browser.clearCookies()
	}

	protected String getPageSource() {
		browser.driver.pageSource
	}

	protected void assertContentContains(String expected) {
		assert $().text().contains(expected)
	}

	// used to verify hidden content like menus and jGrowl flash messages
	protected void assertHtmlContains(String expected) {
		assert browser.driver.pageSource.contains(expected)
	}

	protected void assertContentContainsOne(String expected1, String expected2) {
		assert $().text().contains(expected1) || $().text().contains(expected2)
	}

	protected void assertContentMatches(String regex) {
		assert $().text() ==~ regex
	}

	protected void assertContentDoesNotContain(String unexpected) {
		assert !$().text().contains(unexpected)
	}
}
