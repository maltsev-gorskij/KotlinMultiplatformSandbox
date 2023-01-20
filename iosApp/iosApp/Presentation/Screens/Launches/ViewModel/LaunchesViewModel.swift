//
//  LaunchesViewModel.swift
//  iosApp
//
//  Created by Андрей Лапин on 20.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

final class LaunchesViewModel: ObservableObject {
  private let sdk: SpaceXSDK

  @Published private(set) var launches: [RocketLaunch] = []
  @Published private(set) var toast: Toast?

  private var timer: Timer?

  init(sdk: SpaceXSDK) {
    self.sdk = sdk
    loadLaunches(forceReload: false)
  }

  func loadLaunches(forceReload: Bool) {
    sdk.getLaunches(forceReload: forceReload) { [weak self] launches, error in
      guard let self = self else { return }
      if let error = error {
        self.shownToast(toast: .failed)
        assertionFailure("DEBUT: error is not nil: \(error.localizedDescription)")
        return
      }
      if let launches = launches {
        DispatchQueue.main.async {
          self.launches = launches
          self.shownToast(toast: .success)
        }
      } else {
        self.shownToast(toast: .failed)
      }
    }
  }
  
  private func shownToast(toast: Toast) {
    self.toast = toast
    Timer.scheduledTimer(withTimeInterval: 2, repeats: false) { [weak self] _ in
      guard let self = self else { return }
      self.toast = nil
    }
  }
}
