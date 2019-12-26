package dk.nodes.template.domain.interactors

import dk.nodes.template.models.FacebookUser
import dk.nodes.template.repositories.FacebookRespository
import javax.inject.Inject

class GetSnoozeStatusInteractor @Inject constructor(private val facebookRespository: FacebookRespository)
    : BaseInputAsyncInteractor<FacebookUser?, Array<Any>> {
    override suspend fun invoke(input: FacebookUser?): Array<Any> {
        return facebookRespository.checkUserSnoozeStatus(input)
    }
}