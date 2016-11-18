package com.dasheng.news.di.component;

import android.content.Context;
import com.dasheng.news.di.module.ApplicationModule;
import dagger.internal.Preconditions;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
        value = "dagger.internal.codegen.ComponentProcessor",
        comments = "https://google.github.io/dagger"
)
public final class DaggerApplicationComponent implements ApplicationComponent {
    private Provider<Context> provideApplicationContextProvider;

    private DaggerApplicationComponent(Builder builder) {
        assert builder != null;
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("unchecked")
    private void initialize(final Builder builder) {
        }

    @Override
    public Context getApplication() {
        return provideApplicationContextProvider.get();
    }

    public static final class Builder {
        private ApplicationModule applicationModule;

        private Builder() {}

        public ApplicationComponent build() {
            if (applicationModule == null) {
                throw new IllegalStateException(
                        ApplicationModule.class.getCanonicalName() + " must be set");
            }
            return new DaggerApplicationComponent(this);
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = Preconditions.checkNotNull(applicationModule);
            return this;
        }
    }
}
