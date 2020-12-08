import React from 'react';
import StartPage from './StartPage';
import Enzyme, { shallow, mount, render, configure } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import { Provider } from 'react-redux';
import StartPageReducer from '../components/startpage/StartPageSlice';

configure({ adapter: new Adapter() });

describe('<StartPage />', () => {
    <Provider store={StartPageReducer}>
        <StartPage />
    </Provider>
    // const wrapper = mount(<StartPage />);
    const comp = wrapper.find("[data-test='component-startPage']");
    it('start page validation', () => {
        console.log("exp====",expect(comp.find('input').value));
    });
})
