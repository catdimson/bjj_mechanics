package ru.catdimson.bjjmechanics.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.datasource.RetrofitImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.actions.ActionsInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.actions.ActionsInteractorImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.auth.AuthInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.auth.AuthInteractorImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractorImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.terms.TermsInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.terms.TermsInteractorImpl
import ru.catdimson.bjjmechanics.domain.repository.actions.ActionsRepository
import ru.catdimson.bjjmechanics.domain.repository.actions.ActionsRepositoryImpl
import ru.catdimson.bjjmechanics.domain.repository.auth.AuthRepository
import ru.catdimson.bjjmechanics.domain.repository.auth.AuthRepositoryImpl
import ru.catdimson.bjjmechanics.domain.repository.sections.SectionsRepository
import ru.catdimson.bjjmechanics.domain.repository.sections.SectionsRepositoryImpl
import ru.catdimson.bjjmechanics.domain.repository.terms.TermsRepository
import ru.catdimson.bjjmechanics.domain.repository.terms.TermsRepositoryImpl
import ru.catdimson.bjjmechanics.domain.services.actions.ActionsServiceImpl
import ru.catdimson.bjjmechanics.domain.services.auth.AuthorizationService
import ru.catdimson.bjjmechanics.domain.services.auth.AuthorizationServiceImpl
import ru.catdimson.bjjmechanics.viewmodel.actions.ActionsViewModel
import ru.catdimson.bjjmechanics.viewmodel.auth.AuthViewModel
import ru.catdimson.bjjmechanics.viewmodel.sections.SectionDetailsViewModel
import ru.catdimson.bjjmechanics.viewmodel.sections.SectionsViewModel
import ru.catdimson.bjjmechanics.viewmodel.terms.TermDetailsViewModel
import ru.catdimson.bjjmechanics.viewmodel.terms.TermsViewModel

val generals = module {
    single<DataSource> { RetrofitImpl() }
    single<AuthorizationService> { AuthorizationServiceImpl() }
    single<AuthRepository> { AuthRepositoryImpl(dataSource = get()) }
    single<AuthInteractor> { AuthInteractorImpl(repository = get()) }
}

val actionsScreen = module {
    scope(named("actionsScope")) {
        scoped<ActionsRepository> { ActionsRepositoryImpl(dataSource = get()) }
        scoped<ActionsInteractor> { ActionsInteractorImpl(repository = get()) }
        factory {
            ActionsViewModel(
                interactor = get(),
                actionService = ActionsServiceImpl(),
                application = get()
            )
        }
    }
}

val sectionsScreen = module {
    scope(named("sectionsScope")) {
        scoped<SectionsRepository> { SectionsRepositoryImpl(dataSource = get()) }
        scoped<SectionsInteractor> { SectionsInteractorImpl(repository = get()) }
        factory { SectionsViewModel(interactor = get()) }
    }
}

val sectionDetailsScreen = module {
    scope(named("sectionDetailsScope")) {
        scoped<SectionsRepository> { SectionsRepositoryImpl(dataSource = get()) }
        scoped<SectionsInteractor> { SectionsInteractorImpl(repository = get()) }
        factory { SectionDetailsViewModel(interactor = get()) }
    }
}

val termsScreen = module {
    scope(named("termsScope")) {
        scoped<TermsRepository> { TermsRepositoryImpl(dataSource = get()) }
        scoped<TermsInteractor> { TermsInteractorImpl(repository = get()) }
        factory { TermsViewModel(interactor = get()) }
    }
}

val termDetailsScreen = module {
    scope(named("termDetailsScope")) {
        scoped<TermsRepository> { TermsRepositoryImpl(dataSource = get()) }
        scoped<TermsInteractor> { TermsInteractorImpl(repository = get()) }
        factory {
            TermDetailsViewModel(
                termsInteractor = get(),
                authInteractor = get(),
                authService = get(),
                application = get()
            )
        }
    }
}

val authScreen = module {
    scope(named("authScope")) {
        factory {
            AuthViewModel(
                interactor = get(),
                authService = get(),
                application = get()
            )
        }
    }
}

