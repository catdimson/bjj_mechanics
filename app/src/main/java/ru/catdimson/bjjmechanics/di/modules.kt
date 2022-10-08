package ru.catdimson.bjjmechanics.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.datasource.RetrofitImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.coaching.CoachesInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.coaching.CoachesInteractorImpl
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractorImpl
import ru.catdimson.bjjmechanics.domain.repository.coaching.CoachesRepository
import ru.catdimson.bjjmechanics.domain.repository.coaching.CoachesRepositoryImpl
import ru.catdimson.bjjmechanics.domain.repository.sections.SectionsRepository
import ru.catdimson.bjjmechanics.domain.repository.sections.SectionsRepositoryImpl
import ru.catdimson.bjjmechanics.viewmodel.coaching.CoachingViewModel
import ru.catdimson.bjjmechanics.viewmodel.sections.SectionsViewModel

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

val coachingScreen = module {
    scope(named("coachingScope")) {
        scoped<CoachesRepository> { CoachesRepositoryImpl(dataSource = get()) }
        scoped<CoachesInteractor> { CoachesInteractorImpl(repository = get()) }
        factory { CoachingViewModel(interactor = get()) }
    }
}

