//
//  ToastView.swift
//  iosApp
//
//  Created by Андрей Лапин on 20.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ToastView: View {
  var toast: Toast
  
  var body: some View {
    Text(toast.text)
      .foregroundColor(.white)
      .padding(.horizontal, 25)
      .padding(.vertical, 13)
      .background {
        Color.black.opacity(0.8)
      }
      .cornerRadius(25)
  }
}
