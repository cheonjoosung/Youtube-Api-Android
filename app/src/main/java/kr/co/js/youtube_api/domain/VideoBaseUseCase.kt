package kr.co.js.youtube_api.domain

abstract class VideoBaseUseCase<in PARAMETER, RESPONSE>
    : BaseUseCase<PARAMETER, Result<RESPONSE>>() {

//    override suspend fun invoke(parameter: PARAMETER): Result<RESPONSE> {
//        return Result<VideoResult>
//    }
}