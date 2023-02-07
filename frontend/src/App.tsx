import React from 'react';
import { Header } from './components/Header/index'
import { GlobalStyles } from './GlobalStyles';
import { Routes, Route, Navigate } from 'react-router-dom';
import { SignInPage } from './pages/SignInPage';
import { SignUpPage } from './pages/SignUpPage';
import { ProfilePage } from './pages/ProfilePage'
import { Footer } from './components/Footer';

function App() {
  return (
    <>
      <Header />
      
      <Routes>
        <Route path='/' element={<Navigate to="/login" />}/>
        <Route path='/login' element={<SignInPage />}/>
        <Route path='/cadastro' element={<SignUpPage />}/>
        <Route path='/perfil' element={<ProfilePage />}/>
      </Routes>
      
      <Footer />
      
      <GlobalStyles/>
    </>
  );
}

export default App;
