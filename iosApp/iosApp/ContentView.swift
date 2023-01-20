import SwiftUI
import shared

struct ContentView: View {
  var body: some View {
    TabView {
      LaunchesView()
        .tabItem {
          Label("Launches", systemImage: "airplane")
        }
      FavoritesView()
        .tabItem {
          Label("Favorites", systemImage: "heart")
        }
      ProfileView()
        .tabItem {
          Label("Profile", systemImage: "person")
        }
    }
  }
}
