# RATING BAR

Google rating bar doesn't perform my requirement, so I decided to do my own one.
I took as a reference this one, done by kanytu [Custom-Rating-Bar](https://github.com/kanytu/custom-rating-bar)

### XML attributes:
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="StarsRatingBar">
        <attr name="num_max_stars" format="integer"/>
        <attr name="current_rate" format="float"/>
        <attr name="icon_star_full" format="reference"/>
        <attr name="icon_star_empty" format="reference|integer"/>
        <attr name="icon_star_half" format="reference|integer"/>
        <attr name="indicator" format="boolean"/>
        <attr name="step_by_half" format="boolean"/>
        <attr name="size_animation" format="float"/>
    </declare-styleable>
</resources>
```
Notice that size_animation, is the star size when you touch it.

### Icons

Icons should be choosen by you. I recommend this icon plugin generator. [Plugin](https://github.com/konifar/android-material-design-icon-generator-plugin)

## License

>Copyright (C) 2015

>José del Pozo

>This program is free software: you can redistribute it and/or modify it under the terms of >the GNU General Public License as published by the Free Software Foundation, either version 3 >of the License, or (at your option) any later version.

>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; >without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See >the GNU General Public License for more details.

>You should have received a copy of the GNU General Public License along with this program. If >not, see http://www.gnu.org/licenses/.

