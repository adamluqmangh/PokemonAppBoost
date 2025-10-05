package adam.pokemon.myapp.network

import java.io.IOException

class NoInternetException: IOException {
    constructor(message: String) : super(message)
}