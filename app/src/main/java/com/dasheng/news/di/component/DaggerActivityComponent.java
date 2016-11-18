package com.dasheng.news.di.component;

import android.app.Activity;
import android.content.Context;
import com.dasheng.news.di.module.ActivityModule;
import com.dasheng.news.mvp.ui.activity.NewsActivity;
import com.dasheng.news.mvp.ui.activity.NewsChannelActivity;
import com.dasheng.news.mvp.ui.activity.NewsDetailActivity;
import com.dasheng.news.mvp.ui.activity.NewsPhotoDetailActivity;
import com.dasheng.news.mvp.ui.activity.PhotoActivity;
import com.dasheng.news.mvp.ui.activity.PhotoDetailActivity;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import dagger.internal.ScopedProvider;
import javax.inject.Provider;
import javax.annotation.Generated;
@Generated(
        value = "dagger.internal.codegen.ComponentProcessor",
        comments = "https://google.github.io/dagger"
)
public final class DaggerActivityComponent implements ActivityComponent {
    private Provider<Context> ProvideActivityContextProvider;

    private Provider<Context> getApplicationProvider;

    private Provider<Activity> ProvideActivityProvider;


    private MembersInjector<NewsActivity> newsActivityMembersInjector;


    private MembersInjector<NewsDetailActivity> newsDetailActivityMembersInjector;


    private MembersInjector<NewsChannelActivity> newsChannelActivityMembersInjector;


    private MembersInjector<PhotoActivity> photoActivityMembersInjector;


    private MembersInjector<PhotoDetailActivity> photoDetailActivityMembersInjector;

    private DaggerActivityComponent(Builder builder) {
        assert builder != null;
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("unchecked")
    private void initialize(final Builder builder) {
        this.getApplicationProvider =
                new Factory<Context>() {
                    private final ApplicationComponent applicationComponent = builder.applicationComponent;

                    @Override
                    public Context get() {
                        return Preconditions.checkNotNull(
                                applicationComponent.getApplication(),
                                "Cannot return null from a non-@Nullable component method");
                    }
                };

         }

    @Override
    public Context getActivityContext() {
        return ProvideActivityContextProvider.get();
    }

    @Override
    public Context getApplicationContext() {
        return getApplicationProvider.get();
    }

    @Override
    public Activity getActivity() {
        return ProvideActivityProvider.get();
    }

    @Override
    public void inject(NewsActivity newsActivity) {
        newsActivityMembersInjector.injectMembers(newsActivity);
    }

    @Override
    public void inject(NewsDetailActivity newsDetailActivity) {
        newsDetailActivityMembersInjector.injectMembers(newsDetailActivity);
    }

    @Override
    public void inject(NewsChannelActivity newsChannelActivity) {
        newsChannelActivityMembersInjector.injectMembers(newsChannelActivity);
    }

    @Override
    public void inject(NewsPhotoDetailActivity newsPhotoDetailActivity) {

    }

    @Override
    public void inject(PhotoActivity photoActivity) {
        photoActivityMembersInjector.injectMembers(photoActivity);
    }

    @Override
    public void inject(PhotoDetailActivity photoDetailActivity) {
        photoDetailActivityMembersInjector.injectMembers(photoDetailActivity);
    }

    public static final class Builder {
        private ActivityModule activityModule;

        private ApplicationComponent applicationComponent;

        private Builder() {}

        public ActivityComponent build() {
            if (activityModule == null) {
                throw new IllegalStateException(ActivityModule.class.getCanonicalName() + " must be set");
            }
            if (applicationComponent == null) {
                throw new IllegalStateException(
                        ApplicationComponent.class.getCanonicalName() + " must be set");
            }
            return new DaggerActivityComponent(this);
        }

        public Builder activityModule(ActivityModule activityModule) {
            this.activityModule = Preconditions.checkNotNull(activityModule);
            return this;
        }

        public Builder applicationComponent(ApplicationComponent applicationComponent) {
            this.applicationComponent = Preconditions.checkNotNull(applicationComponent);
            return this;
        }
    }
}
