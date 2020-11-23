# React 准备

## 启动项目

## 引入JS

```html
<!-- 加载 React。-->
<!-- 注意: 部署时，将 "development.js" 替换为 "production.min.js"。-->
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

<!-- 加载我们的 React 组件。-->
<script src="test.js"></script>
```

### 建立项目

```BASH
npx create-react-app my-app
cd my-app
npm start
```

# React 入门



## 1. Hello World

```jsx
ReactDOM.render(
  <h1>Hello, world!</h1>,
  document.getElementById('root')
);
```



## 2. JSX简介

#### 基础语法

```jsx
// 1. 特定属性可以使用 “” 也可以使用 {} 来使用某个变量
const element = (
    <h1 tabIndex="0">
        Hello, {formatName(user)}
    </h1>
)

// 2. 子元素
// 可以包含一个，也可以使用 () 包含多个
const e1 = <h1>I am `e1`</h1>
const e2 = (
    <div>
        <h1>hello!</h1>
        <h12>I AM `E2`</h12>
    </div>
)
```

#### 防止注入攻击

> React DOM 在渲染所有输入内容之前，默认会进行[转义](https://stackoverflow.com/questions/7381974/which-characters-need-to-be-escaped-on-html)。它可以确保在你的应用中，永远不会注入那些并非自己明确编写的内容。所有的内容在渲染之前都被转换成了字符串。这样可以有效地防止 [XSS（cross-site-scripting, 跨站脚本）](https://en.wikipedia.org/wiki/Cross-site_scripting)攻击。



## 3. 元素渲染

[元素渲染 – React (docschina.org)](https://react.docschina.org/docs/rendering-elements.html)

React 元素是[不可变对象](https://en.wikipedia.org/wiki/Immutable_object)。一旦被创建，你就无法更改它的子元素或者属性。一个元素就像电影的单帧：它代表了某个特定时刻的 UI。

根据我们已有的知识，更新 UI 唯一的方式是创建一个全新的元素，并将其传入 [`ReactDOM.render()`](https://react.docschina.org/docs/react-dom.html#render)。

考虑一个计时器的例子：

```jsx
function tick() {
    const element = (
        <div>
            <h1>Hello, World</h1>
            <h2>It's {new Date().toLocaleTimeString()}.</h2>
        </div>
    )

    ReactDOM.render(element, document.getElementById('root'))
}

setInterval(tick, 1000)
```

这个例子会在 [`setInterval()`](https://developer.mozilla.org/en-US/docs/Web/API/WindowTimers/setInterval) 回调函数，每秒都调用 [`ReactDOM.render()`](https://react.docschina.org/docs/react-dom.html#render)。



## 4. 组件 & Props

### 定义组件

定义组件最简单的方式就是编写 JavaScript 函数：

```jsx
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}
```

该函数是一个有效的 React 组件，因为它接收唯一带有数据的 “props”（代表属性）对象与并返回一个 React 元素。这类组件被称为“函数组件”，因为它本质上就是 JavaScript 函数。

你同时还可以使用 [ES6 的 class](https://developer.mozilla.org/en/docs/Web/JavaScript/Reference/Classes) 来定义组件：

```jsx
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}
```

上述两个组件在 React 里是等效的。

我们将在[下一章节](https://react.docschina.org/docs/state-and-lifecycle.html)中讨论关于函数组件和 class 组件的额外特性。

### 混合组件

```jsx
function App() {
    return (
        <div>
            <WelCome name="A"/>
            <WelCome name="B"/>
            <WelCome name="C"/>
        </div>
    )
}

ReactDOM.render(
    <App />,
    document.getElementById('root')
)
```

### 拆分组件

原组件

```jsx
function Comment(props) {
    return (
        <div className="Comment">
            <div className="UserInfo">
                <img className="Avatar"
                     src={props.author.avatarUrl}
                     alt={props.author.name}
                />
                <div className="UserInfo-name">
                    {props.author.name}
                </div>
            </div>
            <div className="Comment-text">
                {props.text}
            </div>
            <div className="Comment-date">
                {formatDate(props.date)}
            </div>
        </div>
    )
}
```

可以拆分为

```jsx
function Comment(props) {
    return (
        <div className="Comment">

            <UserInfo user={props.author}/>

            <div className="Comment-text">
                {props.text}
            </div>
            <div className="Comment-date">
                {formatDate(props.date)}
            </div>
        </div>
    )
}

function UserInfo(props) {
    <div className="UserInfo">
        <Avatar user={props.user}/>
        <div className="UserInfo-name">
            {props.user.name}
        </div>
    </div>
}


function Avatar(props) {
    return (
        <img className="Avatar"
             src={props.user.avatarUrl}
             alt={props.user.name}
        />
    )
}

```



### 特性

**所有 React 组件都必须像纯函数一样保护它们的 props 不被更改。**

##  5.  State & 生命周期

代码不会重新渲染组件：

```jsx
// Wrong
this.state.comment = 'Hello';
```

而是应该使用 `setState()`:

```jsx
// Correct
this.setState({comment: 'Hello'});
```

此代码可能会无法更新计数器：

```jsx
// Wrong
this.setState({
  counter: this.state.counter + this.props.increment,
});
```

要解决这个问题，可以让 `setState()` 接收一个函数而不是一个对象。这个函数用上一个 state 作为第一个参数，将此次更新被应用时的 props 做为第二个参数：

```jsx
// Correct
this.setState((state, props) => ({
  counter: state.counter + props.increment
}));
```