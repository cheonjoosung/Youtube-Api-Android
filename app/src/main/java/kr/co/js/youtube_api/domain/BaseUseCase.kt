package kr.co.js.youtube_api.domain

abstract class BaseUseCase<in PARAMETER, RESPONSE> {

    protected abstract suspend fun execute(parameter: PARAMETER) : RESPONSE

    protected abstract suspend fun invoke(parameter: PARAMETER) : RESPONSE
}