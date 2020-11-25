class Father extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            name1: '小明',
            name2: '小刚'
        }
    }

    changeName(who) {
        console.log(who)
        this.setState({
            [who]: this.state[who] + 'Pro'
        })
    }

    render() {
        return (
            <div>
                I am father
                <Son name={this.state.name1}
                     bro={this.state.name2}
                     change={() => {
                         this.changeName('name1')
                     }}/>
                <Son name={this.state.name2}
                     bro={this.state.name1}
                     change={() => {
                         this.changeName('name2')
                     }}/>
            </div>
        )
    }
}

class Son extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div>
                I am son, my name is {this.props.name},
                my brother is {this.props.bro}
                <button onClick={() => this.props.change()}>改名</button>
            </div>
        )
    }

}

ReactDOM.render(
    <Father/>,
    document.getElementById('root')
)
