This Android application displays a list of Pokémon fetched from the PokéAPI
.
Users can:
- Browse Pokémon in a list view. 
- Sort Pokémon by name in ascending or descending order 
- Mark Pokémon as favourites and view them later in Favourite screen. 
- Tap a Pokémon to view its image, detailed abilities and descriptions. 
- All data is managed in-memory, so it will reset when the application restarts (no database or local storage used).



View (Fragment) → Handles UI rendering and observes ViewModel state.

ViewModel → Provides data to UI, manages state (LiveData).

Repository → Fetches data from the API and exposes it to ViewModel.

Model → Data classes (e.g., PokemonAbility, AbilityDetail).


Component	          Library / Framework
Language	                 Kotlin
Architecture	              MVVM
Dependency Injection	      Hilt
Networking	          Retrofit + Coroutines
Image Loading            	Glide
Logging	                    Timber
Reactive Data	           LiveData 
UI	                RecyclerView, Fragments, Activity



Assumptions
- Internet access is required for API calls. 
- Ability descriptions are fetched in English only (language.name == "en"). 
- Since persistence isn’t required, favourites are cleared after restart.


How to Run
1. Clone this repository. 
2. Open in Android Studio (latest stable version). 
3. Sync Gradle and ensure you have an internet connection. 
4. Run the app on an emulator or real device.

Endpoints used:
1. GET /pokemon — list of Pokémon 
2. GET /pokemon/{name} — Pokémon details 
3. GET /ability/{id} — individual ability details