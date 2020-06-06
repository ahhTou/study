import {delCookie, getNicknameByUsername, getAllList} from './ajax.js'

import {addByObj, updateOne, delOneByMovieId} from './ajax.js'

import setting from '../_setting/setting.js'


let currentSelectTable = null
let baseData = {
    nickname: null,
    seenListID: null,
    wantListID: null,
}
let allListData = {
    pages: 1,
    currentPages: 1,
    data: null,
    htmlData: null
}
let isLogin = window.localStorage.getItem('isLogin')


window.onload = function () {
    loadSetting();
    addEventListeners();
    checkLoginAndGetList()
}

function loadSetting() {
    let $f = document.getElementById("bannerContent");
    $f.children[0].children[0].innerHTML = setting.iLike1;
    $f.children[1].children[0].innerHTML = setting.iLike2;
    $f.children[2].children[0].innerHTML = setting.iLike3;

}

class SetBtnStyle {
    static  oneIsOpen = false

    static setClear($target, innerHTML) {
        $target.style.transition = ""
        $target.style.transform = ""
        $target.style.backgroundColor = ""
        $target.style.width = ""
        $target.style.height = ""
        $target.style.fontSize = ""
        $target.style.zIndex = ""
        $target.style.color = ""
        $target.style.cursor = ""
        $target.innerHTML = innerHTML
    }

    static setBtnDelStyle(isClear) {
        let $btn_del = document.getElementById("editDel")
        if (isClear) {
            this.oneIsOpen = false
            this.setClear($btn_del, "-")

        } else if (!isClear && !this.oneIsOpen) {
            this.oneIsOpen = true
            $btn_del.style.transition = "all 0.3s cubic-bezier(.6,1.84,.48,.8)"
            $btn_del.style.transform = "scale(1.5)"
            $btn_del.style.backgroundColor = "rgb(197,56,56)"
            $btn_del.style.width = 200 + 'px'
            $btn_del.style.height = 100 + 'px'
            $btn_del.style.color = "white"
            $btn_del.style.zIndex = "10"
            $btn_del.style.position = "relative"
            $btn_del.style.cursor = "unset"
            $btn_del.style.fontSize = "13px"
            $btn_del.innerHTML = ""
            new Promise((resolve, reject) => {
                setTimeout(() => {
                    $btn_del.innerHTML = `<div><span >确认删除吗？</span>
                        <button id="tempYes">Yes</button>
                         /<button id="tempNo">No</button></div>`
                    resolve()
                }, 200)
            }).then(() => {
                document
                    .getElementById("tempYes")
                    .addEventListener("click", (e) => {
                        e.stopPropagation()
                        console.log("点击了删除")
                        delOneByMovieId(currentSelectTable).then((data) => {
                            if (data === true) {
                                console.log("删除成功")
                                this.setBtnDelStyle(true)
                                getAllListAndToShowListService(window.localStorage.getItem('username'))
                            } else {
                                alert("删除失败")
                                console.log("删除失败")
                            }
                        }, err => {
                            alert("删除失败,服务器异常")
                            console.log("删除失败,服务器异常")
                        })

                    })
                document
                    .getElementById("tempNo")
                    .addEventListener("click", (e) => {
                        e.stopPropagation()
                        this.setBtnDelStyle(true)
                    })
            })


        }
    }

    static setBtnAddStyle(isClear) {
        let $btn_add = document.getElementById("editAdd")
        if (isClear) {

            this.oneIsOpen = false
            this.setClear($btn_add, "+")

        } else if (!isClear && !this.oneIsOpen) {
            this.oneIsOpen = true
            $btn_add.style.transition = "all 0.3s cubic-bezier(.6,1.84,.48,.8)"
            $btn_add.style.transform = "scale(1.3)"
            $btn_add.style.width = 180 + 'px'
            $btn_add.style.height = 180 + 'px'
            $btn_add.style.color = "black"
            $btn_add.style.zIndex = "10"
            $btn_add.style.position = "relative"
            $btn_add.style.cursor = "unset"
            $btn_add.style.fontSize = "13px"
            $btn_add.innerHTML = ""
            new Promise((resolve, reject) => {
                setTimeout(() => {
                    $btn_add.innerHTML = `
               <label>名称
                  <input type="text" id="temp_name">
                </label>
                <label>类型
                  <input type="text" id="temp_type">
                </label>
                <label>评分
                  <input type="text" id="temp_score">
                </label>
                <div>
                <span >确认添加吗？</span>
                        <button id="tempYes">Yes</button>
                         /<button id="tempNo">No</button></div>`

                    resolve()
                }, 200)
            }).then(() => {
                document
                    .getElementById("tempYes")
                    .addEventListener("click", (e) => {
                        e.stopPropagation()
                        let obj = {
                            movieName: document.getElementById("temp_name").value,
                            movieType: document.getElementById("temp_type").value,
                            movieScore: document.getElementById("temp_score").value,
                            username: localStorage.getItem("username")
                        }
                        addByObj(obj).then(data => {
                            if (data === true || data === "true") {
                                getAllListAndToShowListService(window.localStorage.getItem('username'))
                                this.setBtnAddStyle(true)
                                allListData.currentPages = allListData.pages
                                setCurrentShowPage()
                            } else {
                                alert("返回值错误")
                            }
                        }, err => {
                            alert("请求失败")
                        })
                    })
                document
                    .getElementById("tempNo")
                    .addEventListener("click", (e) => {
                        e.stopPropagation()
                        this.setBtnAddStyle(true)
                    })
            })
        }


    }

    static setBtnEditStyle(isClear) {
        let $btn_edit = document.getElementById("editEdit")
        if (isClear) {
            this.oneIsOpen = false
            this.setClear($btn_edit, "<div>编辑</div>")
        } else if (!isClear && !this.oneIsOpen) {
            this.oneIsOpen = true
            $btn_edit.style.transition = "all 0.3s cubic-bezier(.6,1.84,.48,.8)"
            $btn_edit.style.transform = "scale(1.3)"
            $btn_edit.style.width = 180 + 'px'
            $btn_edit.style.height = 180 + 'px'
            $btn_edit.style.color = "black"
            $btn_edit.style.zIndex = "10"
            $btn_edit.style.position = "relative"
            $btn_edit.style.cursor = "unset"
            $btn_edit.style.fontSize = "13px"
            $btn_edit.innerHTML = ""
            new Promise((resolve, reject) => {
                setTimeout(() => {
                    $btn_edit.innerHTML = `
                 <label>名称
                  <input type="text" id="temp_name">
                </label>
                <label>类型
                  <input type="text" id="temp_type">
                </label>
                <label>评分
                  <input type="text" id="temp_score">
                </label>
                <span>
                <span >提交编辑吗？</span>
                        <button id="tempYes">Yes</button>
                         /<button id="tempNo">No</button></span>`

                    resolve()
                }, 200)
            }).then(() => {
                document
                    .getElementById("tempYes")
                    .addEventListener("click", (e) => {
                        e.stopPropagation()
                        let obj = {
                            movieName: document.getElementById("temp_name").value,
                            movieType: document.getElementById("temp_type").value,
                            movieScore: document.getElementById("temp_score").value,
                            username: localStorage.getItem("username"),
                            movieID: currentSelectTable
                        }
                        console.log(obj)
                        if (obj.movieName !== null && obj.movieScore !== null && obj.movieType !== null) {
                            console.log("真实提交")
                            updateOne(obj).then(data => {
                                if (data === true || data === "true") {
                                    console.log("编辑成功")
                                    getAllListAndToShowListService(window.localStorage.getItem('username'))
                                    this.setBtnEditStyle(true)
                                } else {
                                    alert("返回值错误")
                                    console.log("返回值错误")
                                }
                            }, err => {
                                alert("请求失败")
                                console.log("请求失败")
                            })
                        } else {
                            console.log("虚假提交")
                            this.setBtnEditStyle(true)
                        }
                    })
                document
                    .getElementById("tempNo")
                    .addEventListener("click", (e) => {
                        e.stopPropagation()
                        this.setBtnEditStyle(true)
                    })
            })
        }


    }

}

function checkLoginAndGetList() {
    if (isLogin === 'true' || isLogin === 'true') {
        console.log('> 通过本地验证登录了')
        let localUsername = window.localStorage.getItem('username')
        getBaseData(localUsername)
        getAllListAndToShowListService(localUsername)
    } else {
        console.log('> 没有登录')
        delCookie()
    }
}

function getTableID(e) {
    let tagName = e.target.tagName
    if (tagName === 'TD' || tagName === 'td') {
        console.log(e.target.parentElement.dataset.movieId);
        setCurrentSelectTable(e.target.parentElement)
    }
}

function setCurrentSelectTable($current) {
    $current.style.backgroundColor = "rgba(255, 255, 255, 1)"
    currentSelectTable = $current.dataset.movieId
    let $table = document.getElementById('tableBody')
    let children = $table.children;
    for (let i = 0; i < children.length; i++) {
        if (children[i].dataset.movieId !== currentSelectTable)
            children[i].style.backgroundColor = ""
    }
}

function clearAllCurrentSelectTable() {
    currentSelectTable = null
    let $table = document.getElementById('tableBody')
    let children = $table.children;
    for (let i = 0; i < children.length; i++) {
        children[i].style.backgroundColor = ""
    }
}

function getAllListAndToShowListService(username) {
    getAllList(username).then(data => {
        allListData.data = data
        allListData.pages = Math.ceil(data.length / 10)

        addTableInnerData()
    }, () => {
        alert("获得'我的看过'列表失败了")
    })
}

function addTableInnerData() {
    let $tableBody = document.getElementById("tableBody")
    let allInnerHtml = []
    for (let i = 0; i < allListData.pages; i++) {
        let innerHtml = []
        for (let a = 0; a < 10; a++) {
            if (allListData.data.length >= (i * 10 + a + 1)) {
                innerHtml.push(
                    `
                    <tr data-movie-id="${allListData.data[(i * 10 + a)].movieID}">
                    <td>${allListData.data[(i * 10 + a)].movieName}</td>
                    <td>${allListData.data[(i * 10 + a)].movieType}</td>
                    <td>${allListData.data[(i * 10 + a)].movieScore}</td>
                    <td>${allListData.data[(i * 10 + a)].seenTime}</td>
                    </tr>
                    `)
            } else {
                break
            }
        }
        allInnerHtml.push(innerHtml.join(''))
    }
    allListData.htmlData = allInnerHtml

    // $tableBody.innerHTML = allInnerHtml[allListData.currentPages]
    setCurrentShowPage()


}

function getBaseData(username) {
    getNicknameByUsername(username).then((data) => {
        let $nickname = document.getElementById('username')
        baseData = data
        $nickname.innerText = baseData.nickname
    })
}

function setCurrentShowPage() {
    console.log("执行了跳转页数")
    let $table = document.getElementById('tableBody')
    let $currentPage = document.getElementById("currentPage")
    if (allListData.currentPages === null || allListData.currentPages === '') {
        allListData.currentPages = 1
        $currentPage.innerHTML = `1/${allListData.pages}`
        $table.innerHTML = allListData.htmlData[0]
    } else {
        $currentPage.innerHTML = `${allListData.currentPages}/${allListData.pages}`
        $table.innerHTML = allListData.htmlData[allListData.currentPages - 1]

    }
}

function addEventListeners() {
    let $table = document.getElementById('tableBody')
    let $btn_add = document.getElementById('editAdd')
    let $btn_edit = document.getElementById('editEdit')
    let $btn_del = document.getElementById("editDel")
    let $exitLogin = document.getElementById('exit')

    let $previousPage = document.getElementById("previousPage")
    let $nextPage = document.getElementById("nextPage")
    let $currentPage = document.getElementById("currentPage")

    $previousPage.addEventListener('click', () => {
        clearAllCurrentSelectTable()
        if (allListData.currentPages !== 1) {
            allListData.currentPages--
            setCurrentShowPage()
        }
    })

    $nextPage.addEventListener('click', () => {
        clearAllCurrentSelectTable()
        if (allListData.currentPages !== allListData.pages) {
            allListData.currentPages++
            setCurrentShowPage()
        }
    })


    $exitLogin.addEventListener('click', () => {
        console.log('> 手动退出')
        window.localStorage.setItem('isLogin', 'false')
        window.location.href = './pages/login.html'
        delCookie()
    })

    $btn_del.addEventListener('click', () => {
        if (currentSelectTable != null && currentSelectTable !== '') {
            SetBtnStyle.setBtnDelStyle(false)
        }
    })

    $btn_add.addEventListener('click', () => {
        SetBtnStyle.setBtnAddStyle(false)
    })

    $btn_edit.addEventListener('click', () => {
        console.log(currentSelectTable)
        if (currentSelectTable != null && currentSelectTable !== '') {
            SetBtnStyle.setBtnEditStyle(false)
        }
    })

    $table.addEventListener('click', (e) => {
        getTableID(e)
    })
}