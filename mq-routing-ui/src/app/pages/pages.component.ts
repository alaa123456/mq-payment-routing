import { Component, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSidenavModule } from '@angular/material/sidenav';
import { RouterOutlet } from '@angular/router';
import { menuSideBar } from '../models/sideBarMenu';
import { NavbarComponent } from '../layouts/nav-bar/nav-bar.component';
import { SidebarComponent } from '../layouts/side-bar/side-bar.component';


@Component({
  selector: 'app-pages',
  standalone: true,
  imports: [
    CommonModule,
    MatSidenavModule,
    RouterOutlet,
    NavbarComponent,
    SidebarComponent
  ],
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.scss']
})
export class PagesComponent {
  public menus: any[] = menuSideBar;
  public isOpened: boolean = true;
  desktopViewWidth: number = 1100;

  @HostListener('window:resize', ['$event.target.innerWidth'])
  onResize(width: number) {
    this.isOpened = width >= this.desktopViewWidth;
  }

  toggleSidebar() {
    this.isOpened = !this.isOpened;
  }
}