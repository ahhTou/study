class List {
  constructor(movieID, movieName) {
    this._movieID = movieID
    this._movieName = movieName
  }
}

let a0 = new List(1, 2)
let a1 = JSON.stringify(a0)
console.log(a1)

/* console.log(typeof new List(1, 2))

let a = {
  a: 1,
}

let b = JSON.stringify(a)
console.log(b)
console.log(typeof b)
 */
