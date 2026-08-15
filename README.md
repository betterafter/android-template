# Android Clean Architecture Template (Compose Based)

안녕하세요! 이 프로젝트는 Jetpack Compose와 Clean Architecture를 기반으로 한 안드로이드 프로젝트 템플릿입니다. 매번 새 프로젝트를 시작할 때마다 아키텍처 구조를 잡는 번거로움을 줄이고, 일관된 구조로 개발을 시작할 수 있도록 만들었습니다.

## 1. 프로젝트 구조 살펴보기
일단 이 프로젝트는 관심사 분리를 위해 레이어별로 모듈이 나누어져 있어요. `packages` 폴더를 보면 확인하실 수 있습니다.

*   **domain**: 가장 핵심이 되는 순수한 레이어예요. 안드로이드 의존성 없이 순수 코틀린으로만 구성되며, Entity와 UseCase, 그리고 Repository 인터페이스가 위치합니다.
*   **data**: 데이터를 가져오고 저장하는 역할을 해요. API 통신(Retrofit)이나 로컬 DB 작업, 그리고 domain 레이어의 Repository 인터페이스를 실제로 구현하는 곳입니다.
*   **presentation**: 사용자와 상호작용하는 UI 레이어예요. Compose를 사용해 화면을 그리고, ViewModel을 통해 UI 상태를 관리합니다.
*   **design**: 앱의 디자인 시스템을 담당해요. 공통으로 사용하는 Composable 컴포넌트나 테마(Color, Typography 등) 설정을 여기서 관리하면 됩니다.
*   **app**: 모든 모듈을 하나로 조립하는 곳이에요. Hilt를 이용한 의존성 주입(DI) 설정과 앱의 진입점이 위치합니다.

<img width="1142" height="507" alt="스크린샷 2026-08-15 오후 3 50 21" src="https://github.com/user-attachments/assets/e4bb79ae-968d-4675-8b9b-35ea81a9f6ff" />


## 2. 프로젝트 시작하기
GitHub의 **Use this template** 기능을 통해 새 프로젝트를 만드셨다면, 아래 순서대로 설정을 진행해주세요.

1.  **패키지 명 변경**: 현재 기본 패키지 명은 `com.betterafter.template`으로 되어 있어요. 프로젝트 성격에 맞춰서 전체적으로 패키지 명을 수정해야 합니다.
    *   **팁**: Android Studio의 `Refactor > Rename` 기능을 사용하면 한꺼번에 안전하게 바꿀 수 있어요.
2.  **의존성 확인**: `gradle/libs.versions.toml` 파일에 버전 관리가 되어 있습니다. 최신 버전이 필요한 경우 여기서 수정해주시면 됩니다.

## 3. 개발 가이드라인
이 구조를 따라가면서 기능을 추가할 때는 보통 이런 순서로 진행하면 좋습니다.

1.  **Domain 작성**: 일단 구현하려는 기능의 UseCase와 필요한 데이터 모델(Entity)을 `domain` 모듈에 먼저 정의하세요.
2.  **Data 구현**: `domain`에서 만든 인터페이스를 바탕으로 API나 DB 연동 로직을 `data` 모듈에서 구현합니다.
3.  **UI 구성**: `presentation` 모듈에서 UI를 그리고 ViewModel에 UseCase를 주입받아 연결합니다. 이 때 공통 디자인 요소는 `design` 모듈에 있는 것들을 꺼내 쓰면 돼요.

참고로 이 템플릿은 의존성 주입을 위해 **Hilt**를 사용하고 있어요. 새로운 모듈을 추가하거나 의존 관계를 설정할 때 레이어 간의 참조 방향(Presentation -> Domain <- Data)이 틀어지지 않도록 주의해주시기 바랍니다.
