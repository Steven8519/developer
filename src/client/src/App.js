import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import {getAllDevelopers} from "./test";

class App extends Component {

    render() {
        getAllDevelopers().then(res => res.json().then(developers => {
            console.log(developers);
        }));
        return <h1>Hello from react</h1>
    }
}

export default App;
