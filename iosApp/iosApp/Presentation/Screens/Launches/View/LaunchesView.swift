//
//  LaunchesView.swift
//  iosApp
//
//  Created by Андрей Лапин on 20.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LaunchesView: View {
  @StateObject private var viewModel: LaunchesViewModel = LaunchesViewModel(sdk: DIManager.shared.spaceXSDK)
  var body: some View {
    NavigationView {
      ZStack(alignment: .bottom) {
        List(viewModel.launches) { launch in
          RocketLaunchRow(rocketLaunch: launch)
        }
        .refreshable {
          viewModel.loadLaunches(forceReload: true)
        }
        if let toast = viewModel.toast {
          ToastView(toast: toast)
        }
      }
      .navigationBarTitle("SpaceX Launches")
    }
  }
}

struct LaunchesView_Preview: PreviewProvider {
  static var previews: some View {
    LaunchesView()
  }
}
