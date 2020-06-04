export default class List {
    get movieID() {
        return this._movieID;
    }

    set movieID(value) {
        this._movieID = value;
    }

    get movieName() {
        return this._movieName;
    }

    set movieName(value) {
        this._movieName = value;
    }

    get movieType() {
        return this._movieType;
    }

    set movieType(value) {
        this._movieType = value;
    }

    get movieScore() {
        return this._movieScore;
    }

    set movieScore(value) {
        this._movieScore = value;
    }

    get seenTime() {
        return this._seenTime;
    }

    set seenTime(value) {
        this._seenTime = value;
    }

    get username() {
        return this._username;
    }

    set username(value) {
        this._username = value;
    }

    constructor(movieID, movieName,  movieType, movieScore, seenTime,  username) {
        this._movieID = movieID;
        this._movieName = movieName;
        this._movieType = movieType;
        this._movieScore = movieScore;
        this._seenTime = seenTime;
        this._username = username;
    }
}