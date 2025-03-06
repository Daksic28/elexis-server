package info.elexis.jaxrs.service.internal;

import ch.elexis.core.jaxrs.filter.AbstractCombinedOauthJwtContextSettingFilter;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "skipPattern", value = "/(elexis|public)/.*") })
public class JaxRsJerseyServletCombinedFilter extends AbstractCombinedOauthJwtContextSettingFilter {

}
