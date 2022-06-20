import React from 'react';
import Nav from '../components/Nav';
import { Outlet } from 'react-router-dom';
import ProfileBox from '../components/userForm/ProfileBox';
import Popular from '../components/popular/Popular';
import '../css/Main.css';

function MainPage() {
  return (
    <div>
      <header>
        <img className="logo" src="img/logo1.png" alt="logo" />
      </header>
      <Nav />
      <div className="main-container">
        <section className="section-left">
          <ProfileBox />
        </section>
        <section className="section-middle">
          <Outlet />
        </section>
        <section className="section-right">
          <Popular />
        </section>
      </div>
    </div>
  );
}

export default MainPage;
