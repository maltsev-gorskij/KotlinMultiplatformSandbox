import SwiftUI
import shared

@main
struct iOSApp: App {
  init() {
    DIManager.configure()
  }

  var body: some Scene {
    WindowGroup {
      ContentView(viewModel: .init(sdk: DIManager.shared.spaceXSDK))
    }
  }
}

final class DIManager {

  static func configure() {
    KoinInitializerKt.initializeKoin()
  }

  static let shared = DIManager()

  init() {
  }

  let spaceXSDK = SpaceXSDK()
}
