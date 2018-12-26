import {Directive, HostBinding, HostListener} from '@angular/core';

@Directive({
  selector: '[appSettingsDirective]'
})
export class SettingsDirective {
  @HostBinding('class.open') isOpen = false;

  @HostListener('click') toggleOpen() {
    this.isOpen = !this.isOpen;

  }


}
