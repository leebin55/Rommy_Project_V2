import React from 'react';
import MainNav from '../components/main/MainNav';
import { Outlet } from 'react-router-dom';
import ProfileBox from '../components/main/ProfileBox';
import Popular from '../components/main/Popular';
import '../css/main/Main.css';

function MainPage() {
  return (
    <div>
      <header>
        <img className="logo" src="img/logo1.png" alt="logo" />
      </header>
      <MainNav />
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
