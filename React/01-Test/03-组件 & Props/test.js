// 定义组件最简单的方式就是编写 JavaScript 函数：
function WelCome(props) {
    return <h1>hell, {props.name}</h1>
}

// 你同时还可以使用 ES6 的 class 来定义组件：
class WelCome2 extends React.Component {
    render() {
        return <h1>hello, { this.props.name }</h1>
    }
}

// 之前，我们遇到的 React 元素都只是 DOM 标签：
const element1 = <div />;

// 不过，React 元素也可以是用户自定义的组件：
const element2 = <WelCome name="Sara" />;

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
    // element2,
    <App />,
    document.getElementById('root')
)
