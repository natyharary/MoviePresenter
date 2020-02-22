# MoviePresenter
A playground app for learning how to use Kotlin and MVVM architecture components.

Uses the TMDB REST API.

Connects via Retrofit2, retrieves details about movies and displays in a RecyclerView.

## How to use this repo?

Change the following app gradle file's buildConfigFields values:

'''
prod {
        buildConfigField 'String', 'API_KEY', '"TMDBKEYPROD"'
     }
net {
        buildConfigField 'String', 'API_KEY', '"TMDBKEYDEBUG"'
    }
'''

To your user's API key values.
