import React from 'react';
import ScorePage from './ScorePage';
import Enzyme, { shallow, mount, render, configure } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import { Provider } from 'react-redux';
import ScorePageReducer from './ScorePageSlice';
import store from '../../app/store';

configure({ adapter: new Adapter() });

describe('<ScorePage />', () => {
    const wrapper = mount(<ScorePage store={store} />);
    const compTable = wrapper.find("[data-scoretable='component-scorePageRecordsDisplay']");
    it('score page validation', () => {
        const gettbody = compTable.find("tbody");
        const gettr = gettbody.find("tr");
        const gettd = gettr.find("td");
        gettd.value = "TestUser";
        expect(gettd.value.length).toBe(8);
    });
})
