#!/usr/env perl

use Mojolicious::Lite;
use Mojo::Util qw(sha1_sum);
use Mojo::JSON;
use IO::File;

post '/wtf' => sub {
    my ($self) = @_;

    my $report = Mojo::JSON->new->encode({ 
        timestamp => time(), 
        file => $self->param('file'),
        code => $self->param('code') 
        comment => $self->param('comment') 
    });
    IO::File->new('> data/' . sha1_sum($report))->print($report);

    $self->rendered(201);
};

app->start;
