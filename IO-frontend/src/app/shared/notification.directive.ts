import {Directive, HostBinding, HostListener} from '@angular/core';

@Directive({
  selector: '[appNotificationDirective]'
})
export class NotificationDirective {
  @HostBinding('class.open') isOpen = false;

  @HostListener('click') toggleOpen() {
    this.isOpen = !this.isOpen;

  }


}
