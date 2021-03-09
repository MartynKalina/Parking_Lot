class Parking(var size:Int){
    var parking: Array<Spot> = Array(size) {Spot()}
    var freeSpot = 0

    fun parkCar(id:String,color: String){

        parking[freeSpot] = Spot(freeSpot+1,false,Car(id,color))
        freeSpot++
        println("$color car parked in spot ${freeSpot}.")
    }

    fun listParking(){
        for (i in 0 until this.size){
            if (parking[i].id != 0) {
                println("${parking[i].id} ${parking[i].car} ")
            }
        }
    }

    fun isParkingFree(): Boolean{
        var result = true
        for (i in 0 until this.size) if (!(parking[i].availability)){
            result = false
        }
        return result
    }

    fun leaveCar(s: Int) {
        parking[s - 1].id = 0
        println("Spot $s is free.")
    }

    fun spotByColor(s: String) {
        var array = emptyArray<Int>()
        for (i in 0 until this.size){
            if (parking[i].id != 0 && parking[i].car?.color?.equals(s)!!) {
                array+= parking[i].id
            }
        }
        if (array.isEmpty()){
            println("No cars with color $s were found.")
        }else{
            println(array.joinToString())
        }

    }

    fun spotByReq(s: String) {
        var arrayColors = emptyArray<Int>()
        for (i in 0 until this.size){
            if (parking[i].id != 0 && parking[i].car?.id?.equals(s)!!) {
                arrayColors += parking[i].id
            }
        }
        if (arrayColors.isEmpty()){
            println("No cars with registration number $s were found.")
        }else{
            println(arrayColors.joinToString())
        }
    }

    fun regByColor(s: String) {
        var arrayRegs = emptyArray<String>()
        for (i in 0 until this.size){
            if (parking[i].id != 0 && parking[i].car?.color?.equals(s)!!) {
                arrayRegs += parking[i].car!!.id
            }
        }
        if (arrayRegs.isEmpty()){
            println("No cars with color $s were found.")
        }else{
            println(arrayRegs.joinToString())
        }

    }

}

class Spot(var id: Int = 0, var availability:Boolean = true, var car: Car? = null) {
    override fun toString(): String {
        return "Spot(id=$id, availability=$availability, car=$car)"
    }
}

class Car(var id: String, var color:String){
    override fun toString(): String {
        return "$id $color"
    }
}


fun main() {
    var exit = true
    var parking1 = Parking(0)

    do {
        var input = readLine()!!.split(" ")
        when (input[0]) {
            "create" -> {
                var spots = input[1].toInt()
                parking1 = Parking(spots)
                println("Created a parking lot with $spots spots.")
            }
            "status" -> {
                if (parking1.size == 0){
                    println("Sorry, parking lot is not created.")
                }else{
                    if (parking1.isParkingFree()){
                        println("Parking lot is empty.")
                    }else{
                        parking1.listParking()
                    }
                }
            }
            "park" -> {
                if (parking1.size == 0){
                    println("Sorry, a parking lot has not been created.")
                }else{
                    parking1.parkCar(input[1],input[2].toUpperCase())
                }
            }
            "leave" -> {
                if (parking1.size == 0){
                    println("Sorry, a parking lot has not been created.")
                }else{
                    parking1.leaveCar(input[1].toInt())
                }
            }
            "spot_by_color" -> {
                if (parking1.size == 0){
                    println("Sorry, a parking lot has not been created.")
                }else{
                    parking1.spotByColor(input[1].toUpperCase())
                }
            }
            "spot_by_reg" -> {
                if (parking1.size == 0){
                    println("Sorry, a parking lot has not been created.")
                }else{
                    parking1.spotByReq(input[1])
                }
            }
            "reg_by_color" -> {
                if (parking1.size == 0){
                    println("Sorry, a parking lot has not been created.")
                }else{
                    parking1.regByColor(input[1].toUpperCase())
                }
            }
            "exit" -> {exit = false}
            else -> {exit = false}
        }
    }while (exit)
}


