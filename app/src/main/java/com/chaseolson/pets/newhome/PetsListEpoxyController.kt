package com.chaseolson.pets.newhome

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.chaseolson.pets.PetCardBindingModel_
import com.chaseolson.pets.home.model.NewPetListItemViewState

class PetsListEpoxyController(val viewModel: HomeScreenViewModel3): PagedListEpoxyController<NewPetListItemViewState.NewPet>() {
    var idNumber = 0

    override fun buildItemModel(currentPosition: Int, item: NewPetListItemViewState.NewPet?): EpoxyModel<*> {
        return PetCardBindingModel_()
            .id(idNumber++)
            .pet(item)
            .viewModel(viewModel)
    }
}