package io.fabric8.launcher.service.git.api;

import java.util.Optional;

import io.fabric8.launcher.base.identity.Identity;

/**
 * A Service Factory for {@link GitService} instances
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public interface GitServiceFactory {

    /**
     * @return a human-readable name that this service provides
     */
    default String getName() {
        return getDefaultConfig().getName();
    }

    /**
     * Creates a new {@link GitService} with the default authentication.
     *
     * @return the created {@link GitService}
     */
    default GitService create() {
        return create(getDefaultIdentity().orElseThrow(() -> new IllegalStateException("Cannot find the default identity needed in " + getClass().getName() + ".create()")),
                      null,
                      getDefaultConfig());
    }

    /**
     * Creates a new {@link GitService} with the specified,
     * required identity and the logged user, if exists.
     *
     * @param identity the identity used to authenticate the {@link GitService}
     * @param login    the user login. May be null
     * @return the created {@link GitService}
     * @throws IllegalArgumentException If the {@link Identity} is not specified
     */
    default GitService create(Identity identity, String login) {
        return create(identity, login, getDefaultConfig());
    }

    /**
     * Creates a new {@link GitService} with the specified,
     * required identity and the logged user, if exists.
     *
     * @param identity the identity used to authenticate the {@link GitService}
     * @param login    the user login. May be null
     * @param config   the configuration this {@link GitServiceFactory} should use
     * @return the created {@link GitService}
     * @throws IllegalArgumentException If the {@link Identity} is not specified
     */
    GitService create(Identity identity, String login, GitServiceConfig config);

    /**
     * Returns the default identity for the Github service
     *
     * @return an optional {@link Identity}
     */
    Optional<Identity> getDefaultIdentity();

    /**
     * @return the default configuration
     */
    GitServiceConfig getDefaultConfig();

}