//
//  Toast.swift
//  iosApp
//
//  Created by Андрей Лапин on 20.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

enum Toast {
  case success, failed
  
  var text: String {
    switch self {
    case .success:
      return "Success load ✅"
    case .failed:
      return "Success load ❌"
    }
  }
}
