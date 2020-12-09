import React, { Component } from 'react';
import { bindActionCreators } from "redux";
import { connect } from 'react-redux';
import { BrowserRouter as Router, Route, Switch, NavLink } from 'react-router-dom';

import {
    getUserName, gotoGamePage, resetUserName
} from './StartPageSlice';


class StartPage extends Component {
    componentWillMount() {
        this.props.dispatch(resetUserName());
    }
    getUserName(event) {
      this.props.dispatch(getUserName(event.target.value))
    }

    gotoGamePage() {
        this.props.dispatch(gotoGamePage(this.props.userSelectedName, this.props))
    }

  render() {
    return (
        <main role="main" className="SM-home">
            <div className="SM-section px-3">
                <h5 className="font-weight-semiBold text-center px-2 mb-5 SM-txt-white">Welcome to <br></br> Mini Memory Game...!</h5>
                    <div className="SM-formControl-group mb-4">
                    <div className="SM-formLabel SM-txt-white">Enter username</div>
                    <div className="SM-formControl" data-test='component-startPage'>
                        <input className="" type="text" placeholder="Name" onKeyUp={(event) => this.getUserName(event)} />
                    </div>
                    {this.props.userSelectedName === '' ? <div className="SM-validationMsg" data-testmsg='component-startPageValidationMsg'>Please enter username to play</div> : ''}
                    </div>
                <button className="btn SM-btnPrimary SM-btnMedium w-100" onClick={() => this.gotoGamePage()}>Play</button>
		   </div>
	    </main>
    );
  }


}


function mapStateToProps(state) {
  return {
      userSelectedName: state.startpage.userSelectedName
  };
}
function mapDispatchToProps(dispatch) {
  return {
    dispatch,
    actions: bindActionCreators({
    }, dispatch)
  }
}
export default connect(mapStateToProps, mapDispatchToProps)(StartPage);
