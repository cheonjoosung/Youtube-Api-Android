# Youtube-Api for android

This is "Youtube Api Sample" using youtube api(search & video) and used PierfrancescoSoffritti/android-youtube-player.
이 앱은 유튜브 API(search & video)를 사용하여 만든 샘플이고 PierfrancescoSoffritti/android-youtube-player을 사용했습니다.

## Stack & Skill (스택 기술)
- Kotlin & Android
- Retrofit2, Glide, Hilt, ViewBinding
- MVVM + Clean Architectrue, AAC

## App Structure (앱 구조)
|── app
|   └── di
|       ├── DataSourceMoudle.kt
|       ├── NetworkModule.kt
|       ├── RepositoryModule.kt
|       ├── UseCaseModule.kt
|       └── VideoApplication.kt
|
|── buildSrc
|   └── Dependency.kt
|
|── data
|    └── mapper
|    |    └── YoutubeMapper.kt
|    |
|    └── model
|    |    └── YoutubeChannelInfo.kt
|    |    └── YoutubeVideo.kt
|    |    └── YoutubeVideoInfo.kt
|    |
|    └── service
|    |    └── YoutubeApiService
|    |
|    └── source
|    |    └── YoutubeRemoteDataSource.kt
|    |    └── YoutubeRemoteDataSourceImpl.kt
|    |
|    └── utils
|    |    └── Utils.kt
|    |
|    └── YoutubeRepositoryImpl.kt
|
|── domain
|    └── model
|    |    └── local
|    |    └── remote
|    |    |     └── ChannelInfo.kt
|    |    |     └── TrendVIdeoResult.kt
|    |    |     └── Video.kt
|    |    |     └── VideoInfo.kt
|    |    |     └── VideoResult.kt
|    |    |     
|    |    └── ApiResult.kt
|    |
|    └── repository
|    |    └── YoutubeRepository.kt
|    |
|    └── usecase
|    |    └── local
|    |    └── remote
|    |    |    └── ChannelInfoUseCase.kt
|    |    |    └── SearchVideoUseCase.kt
|    |    |    └── TrendVideoUseCase.kt
|    |    |    └── VideoInfoUseCase.kt
|
|── prsentation
|    └── my
|    |    └── MyVideoFragment.kt
|    |    └── MyVideoViewModel.kt
|    |
|    └── player
|    |    └── YoutubePlayerActivity.kt
|    |
|    └── search
|    |    └── SearchAdapter.kt
|    |    └── SearchVideoFragment.kt
|    |    └── SearchVideoViewModel.kt
|    |
|    └── setting
|    |    └── SettingFragment.kt
|    |    
|    └── trend
|    |    └── DiffUtilCallback.kt
|    |    └── TrendVideoAdapter.kt
|    |    └── TrendVideoFragment.kt
|    |    └── TrendVideoViewModel.kt
|    |
|    └── MainActivity.kt
|    └── MainActivityViewModel.kt
└── utils
|    └── VidoeUtil.kt

## Sample
|<img src="https://github.com/cheonjoosung/Youtube-Search-Android/blob/master/image/trend.jpeg">|<img src="https://github.com/cheonjoosung/Youtube-Search-Android/blob/master/image/search.jpeg">|
|-|-|


## How to
1. Go to [Google API](https://console.cloud.google.com/apis)
2. Then, make api & auto information for youtube data v3. Copy API Key
3. "Open SearchRepository.kt" paste "apiKey"
4. Run App 

## 참조(Reference)

- [Google API](https://console.cloud.google.com/apis)
- [Youtube data api v3](https://developers.google.com/youtube/v3/docs)
- [유튜브 프래그먼트 플레이어](https://github.com/PierfrancescoSoffritti/android-youtube-player)


## 라이센스(License)
MIT License

Copyright (c) 2018 Pierfrancesco Soffritti

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


