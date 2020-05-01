package com.chaseolson.pets.home

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.chaseolson.pets.PetCardBindingModel_
import com.chaseolson.pets.home.models.PetListItemViewState

class PetsListEpoxyController(val viewModel: HomeScreenViewModel) :
    PagedListEpoxyController<PetListItemViewState.Pet>() {
    override fun buildItemModel(
        currentPosition: Int,
        item: PetListItemViewState.Pet?
    ): EpoxyModel<*> {
        requireNotNull(item)
        return PetCardBindingModel_()
            .id(currentPosition)
            .pet(item)
            .viewModel(viewModel)
    }
}