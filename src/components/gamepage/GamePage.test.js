import React from 'react';
import GamePage from './GamePage';
import Enzyme, { shallow, mount, render, configure } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import { Provider } from 'react-redux';
import GamePageReducer from './GamePageSlice';
import store from '../../app/store';

configure({ adapter: new Adapter() });

describe('<GamePage />', () => {
    const wrapper = mount(<GamePage store={store} />);
    const compGamePage = wrapper.find("[datagamepage='component-gamepagedisplay']");
    it('game page card validation', () => {
        const inputGamePage = compGamePage.hasClass("SM-card");
        expect(inputGamePage).toBe(true);
    });

    const compScorePage = wrapper.find("[datagamescore='component-gamescoredisplay']");
    it('game page score validation', () => {
        const scoreDisplay = compScorePage;
        scoreDisplay.value = 10;
        expect(scoreDisplay.value).toBe(10);
    });
})
