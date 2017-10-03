var Config = {
    host: "http://localhost:8080",
    comboRepository: "/combo",
    logger: {
        level: "DEBUG"
    }
};

function Logger(src) {
    this.source = src;
    this.level = Config.logger.level;
    this.debug = function(str) {
        if (this.level == "DEBUG") console.log(str);
    };
};

var _logger = new Logger();

var ComboElement = React.createClass({
    _levels: ["", "super", "power", "ninja", "turbo", "neo", "ultra", "hyper", "mega", "multi", "alpha"],
    
    getInitialState: function() {
        return { combo: {} };
    },

    componentDidMount: function() {
        this.setState({ combo: this.props.combo });
    },

    componentWillUnmount: function() {
    },

    activateCombo: function(e) {
        e.preventDefault();
        
        var self = this;
        const combo = this.state.combo;
        _logger.debug(combo.name + " clicked");
        
        // Update combo locally
        combo.hits+=1;
        combo.level = Math.floor(combo.hits/combo.hitsLimit);
        
        // Update combo in backend
        fetch(Config.host + Config.comboRepository + "/" + combo.id, {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(combo)
        }).then(function(response) {
            return response.json()
        }).then(function(data) {
            self.setState({ combos: data });
            _logger.debug(self.state);
        });
    },
    
    render: function() {
        if (this.state.combo.id == null) {
            return (
                <div className="screen">
                    <p>Element loading ...</p>
                </div>
            );
        }
        
        var progress = ( (this.state.combo.hits-(this.state.combo.level*this.state.combo.hitsLimit)) *100 ) / this.state.combo.hitsLimit;
        var qualifier = this._levels[this.state.combo.level];
        _logger.debug({progress: progress, qualifier: qualifier});

        return (
            <div className="combo">
                <br/>
                <br/>
                <div className="center-align">
                    <a className="waves-effect waves-light btn" onClick={this.activateCombo}>
                        {this.state.combo.name}
                    </a>
                </div>
                <p className="combo">{qualifier} {this.state.combo.hits} hit combo</p>
                <div className="progress">
                    <div className="determinate" style={{width: progress+"%"}}></div>
                </div>
            </div>
		);
    }
});

var ComboApp = React.createClass({
    
    getInitialState: function() {
        return { combos: [] };
    },

    componentDidMount: function() {
        _logger.debug("loading " + Config.host + Config.comboRepository);
        
        var self = this;
        fetch(Config.host + Config.comboRepository)
            .then(function(response) {
                return response.json()
            })
            .then(function(data) {
                self.setState({ combos: data });
                _logger.debug(self.state);
            });
    },

    componentWillUnmount: function() {
        this.serverRequest.abort();
    },
    
    render: function() {
        if (this.state.combos.length <= 0) {
            return (
                <div className="screen">
                    <h1>Combo</h1>
                    <p>Welcome to the combo app. You do not seem to have made any combo yet. Start right <a href="#">here</a></p>
                </div>
            );
        }
        
        var comboElements = this.state.combos.map(function(elt, i) {
            return (
                <ComboElement key={i} combo={elt} />
            );
        });

        return (
            <div className="screen">
                {comboElements}
            </div>
		);
    }
});


ReactDOM.render(
  <ComboApp />,
  document.getElementById("app")
);