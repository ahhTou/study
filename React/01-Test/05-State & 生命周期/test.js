function Clock0(props) {
    return (
        <div>
            <h1>Hello, world!</h1>
            <h2>It is {props.date.toLocaleTimeString()}.</h2>
        </div>
    )
}


function tick0() {
    ReactDOM.render(
        <Clock date={new Date()}/>,
        document.getElementById('root')
    )
}


class Clock extends React.Component {
    constructor(props) {
        super(props)
        this.state = {date: new Date()}
    }

    tick() {
        this.setState({
            date: new Date()
        })
    }

    // 生命周期
    // 当组件插入DOM中
    componentDidMount() {
        this.timerID = setInterval(
            () => this.tick(),
            1000
        )
    }

    // 当从DOM中移除时
    componentWillUnmount() {
        clearInterval(this.timerID)
    }

    render() {
        return (
            <div>
                <h1>Hello, world!</h1>
                <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
            </div>
        )

    }
}

ReactDOM.render(
    <Clock/>,
    document.getElementById('root')
)

// setInterval(tick0, 1000)
