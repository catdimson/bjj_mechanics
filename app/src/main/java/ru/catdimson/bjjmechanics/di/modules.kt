package ru.catdimson.bjjmechanics.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.datasource.RetrofitImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.coaching.CoachesInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.coaching.CoachesInteractorImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractorImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.terms.TermsInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.terms.TermsInteractorImpl
import ru.catdimson.bjjmechanics.domain.repository.coaching.CoachesRepository
import ru.catdimson.bjjmechanics.domain.repository.coaching.CoachesRepositoryImpl
import ru.catdimson.bjjmechanics.domain.repository.sections.SectionsRepository
import ru.catdimson.bjjmechanics.domain.repository.sections.SectionsRepositoryImpl
import ru.catdimson.bjjmechanics.domain.repository.terms.TermsRepository
import ru.catdimson.bjjmechanics.domain.repository.terms.TermsRepositoryImpl
import ru.catdimson.bjjmechanics.viewmodel.sections.SectionDetailsViewModel
import ru.catdimson.bjjmechanics.viewmodel.sections.SectionsViewModel
import ru.catdimson.bjjmechanics.viewmodel.terms.TermDetailsViewModel
import ru.catdimson.bjjmechanics.viewmodel.terms.TermsViewModel

val generals = module {
    single<DataSource> { RetrofitImpl() }
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
        factory { TermDetailsViewModel(interactor = get()) }
    }
}

