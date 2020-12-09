import React from 'react';
import StartPage from './StartPage';
import Enzyme, { shallow, mount, render, configure } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import { Provider } from 'react-redux';
import StartPageReducer from './StartPageSlice';
import store from '../../app/store';

configure({ adapter: new Adapter() });

describe('<StartPage />', () => {
    const wrapper = mount(<StartPage store={store} />);
    const comp = wrapper.find("[data-test='component-startPage']");
    it('start page input validation', () => {
        const input = comp.find("input");
        input.instance().value = "Test";
        expect(input.instance().value.length).toBe(4);
    });

    const compMsg = wrapper.find("[data-testmsg='component-startPageValidationMsg']");
    it('start page input validation message', () => {
        const input = comp.find("input");
        input.instance().value = "";
        expect(input.instance().value.length).toBe(0);
        expect(compMsg.text().includes(compMsg.text())).toBe(true);
    });
})
