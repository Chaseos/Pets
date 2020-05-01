package com.chaseolson.pets.home

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.chaseolson.pets.PetCardBindingModel_
import com.chaseolson.pets.home.models.PetListViewState

class PetsListEpoxyController(val viewModel: HomeScreenViewModel) :
    PagedListEpoxyController<PetListViewState.Pet>() {
    override fun buildItemModel(currentPosition: Int, item: PetListViewState.Pet?
    ): EpoxyModel<*> {
        requireNotNull(item)
        return PetCardBindingModel_()
            .id(currentPosition)
            .pet(item)
            .viewModel(viewModel)
    }
}