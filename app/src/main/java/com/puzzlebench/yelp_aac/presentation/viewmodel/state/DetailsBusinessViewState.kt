package com.puzzlebench.yelp_aac.presentation.viewmodel.state

sealed class DetailsBusinessViewState {
    class ShowErrorMessage(val message: String) : DetailsBusinessViewState()
    class ShowCategories(val categories: List<String>) : DetailsBusinessViewState()
    class ShowPhotos(val photos: List<String>) : DetailsBusinessViewState()

}