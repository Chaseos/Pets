package com.chaseolson.pets.ui.home

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.chaseolson.pets.PetCardBindingModel_

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